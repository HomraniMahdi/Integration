
export class Entretien {
    idEntretien?: number;
    dateEntretienR: Date;
    dateEntretienT: Date;
      constructor(
        idEntretien?: number,
        dateEntretienR: Date = new Date(),

        dateEntretienT: Date = new Date(),
      ) {
        this.idEntretien = idEntretien;
        this.dateEntretienT = dateEntretienT;
        this.dateEntretienR = dateEntretienR;
      }
    }