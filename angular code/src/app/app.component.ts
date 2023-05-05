import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup,Validators, } from '@angular/forms';
import { file } from './file';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  ngOnInit(): void {
   
  }
  title = 'dms';

  files: Array<file> | undefined;
  
  name:String | undefined;
  path:String | undefined ;
  file: File | undefined;

  uploadForm = new FormGroup({

    name: new FormControl(undefined, Validators.required),
    file: new FormControl(undefined, Validators.required),
   
  })

    selectedFile: File = new File([], '');


   formData = new FormData();



  






  constructor
  (
    private httpClient: HttpClient
  )
  {
    this.files = [];
    this.name = undefined;
    this.path = '';
 
  }

  
  fileChange(event: any): void {
    this.selectedFile = event.target.files[0];
  }


  onSubmit(): void {
    const formData = new FormData();
    const fileValue = this.uploadForm.get('file')?.value;

    
    if(fileValue) {
      formData.append('file', fileValue,fileValue);
      this.httpClient.post<any>('http://localhost:8081/file/upload', formData).subscribe(
        response => {
          console.log('File upload successful');
          console.log('Response:', response);
        },
        error => {
          console.error('Error uploading file', error);
        }
      );
    } else {
      // console.error('File value is null or undefined');
      
      
    }
  }


  
  
}

