package tn.esprit.devminds.Controller;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devminds.Entities.CheckoutPayment;
import tn.esprit.devminds.Service.EventServiceImp;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:4200") // Allow CORS requests from this origin
@AllArgsConstructor
public class StripeController {
    // create a Gson object
    public static Gson gson = new Gson();

    @Autowired
    EventServiceImp eventService ;

    @PostMapping("/payment")
    /**
     * Payment with Stripe checkout page
     *
     * @throws StripeException
     */
    public String paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException {
        // We initilize stripe object with the api key
        init();
        // We create a  stripe session parameters
        SessionCreateParams params = SessionCreateParams.builder()
                // We will use the credit card payment method
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).
                setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(payment.getCancelUrl())
                .addLineItem(SessionCreateParams.
                        LineItem.builder()
                        .setQuantity(payment.getQuantity())
                        .setPriceData(SessionCreateParams.
                                LineItem.PriceData.builder().
                                setCurrency(payment.getCurrency())
                                .setUnitAmount(payment.getAmount())
                                .setProductData(SessionCreateParams.LineItem.
                                        PriceData.ProductData.builder().
                                        setName(payment.getName()).build()).build()).build()).build();
        // create a stripe session
        Session session = Session.create(params);
        Long eventId = payment.getEventId();
        eventService.updateNbParticipantsAfterPayment(eventId); // Assuming you have an eventId


        Map<String, String> responseData = new HashMap<>();
        // We get the sessionId and we putted inside the response data you can get more info from the session object
        responseData.put("id", session.getId());
        // We can return only the sessionId as a String
        return gson.toJson(responseData);
    }

    private static void init() {
        Stripe.apiKey = "sk_test_51P5zjBBHlnmiWWCmKErRprl4XszLYXCWhf9NvkaXaBZyiWaMgL2eradIDdpc96syP0v9NTCv91Qy5cv58EKM2fMb00UnNaeRMw";
    }
}
