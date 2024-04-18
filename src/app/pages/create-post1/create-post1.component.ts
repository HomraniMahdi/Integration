import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PostService } from 'src/app/services/post.service';


@Component({
  selector: 'app-create-post1',
  templateUrl: './create-post1.component.html',
  styleUrls: ['./create-post1.component.css']
})
export class CreatePost1Component {
  postForm!: FormGroup;
  tags:string[]= [];
  constructor(private fb: FormBuilder,
    private router :Router,
    private snackbar:MatSnackBar,
    private postServices:PostService){}
  ngOnInit(){
    this.postForm =this.fb.group({
      name:[null,Validators.required],
      content:[null,[Validators.required,Validators.maxLength(5000)]],
      img:[null,Validators.required],
      postedBy:[null,Validators.required]
    })
  }
  add(event:any){
    const value=(event.value || '' ).trim();
    if(value){
      this.tags.push(value);
    
    }
    event.chipInput!.clear();

  } 
  remove(tag:any){
    const index=this.tags.indexOf(tag);

    if(index>=0)
      {
        this.tags.splice(index,1);
      }
  }
 createPost(){
  const data=this.postForm.value;
  data.tags =this.tags;
  this.postServices.createNewPost(data).subscribe(res=>{
    this.snackbar.open("Post Created Successfully !!!","ok");
    this.router.navigateByUrl("/");
  },error=>{
    this.snackbar.open("Something Went Wrong!!!","Ok")
  })
 }

}
