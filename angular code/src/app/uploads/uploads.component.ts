import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup,Validators } from '@angular/forms';
import { FileUploadService } from '../file-upload.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';





@Component({
  selector: 'app-uploads',
  templateUrl: './uploads.component.html',
  styleUrls: ['./uploads.component.css']
})
export class UploadsComponent implements OnInit {
  profileForm!: FormGroup;
  constructor(private fb: FormBuilder,private http: HttpClient)
  {
    this.profileForm= this.fb.group({
      name:'',
      file : new FormControl(Validators.required)

    });
  }
  ngOnInit(): void {
    this.formInit();

    this.print();
    

    // test: Boolean;

    // show : Boolean;
  }
  formInit():void{
    this.profileForm = this.fb.group({
      name:'',
      file : new FormControl(' ',Validators.required)
    });
  }
  test=false
  previewImage: string | null = null;
  isImageSelected = false;
  OnSelectedFile(event:any) {
    console.log(event.target.files);
    const file = event.target.files[0];
    
    if (file.type.includes('image/')) {
      this.isImageSelected = true;
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.previewImage = reader.result as string;
      };
    } else {
      this.isImageSelected = false;
    }

    if(event.target.files.length > 0)
    {
      const file = event.target.files[0];
      this.profileForm.get('file')?.setValue(file);

      this.profileForm.patchValue({
        file: file
      });

    }
    else
    {
      
    }


    
 
    
  }

  get file()
  {
    return this.profileForm.get('file');
  }
  

  onSubmit() {
    const formData = new FormData();
    formData.append('file', this.profileForm.get('file')?.value);
    formData.append('name', this.profileForm.get('name')?.value);

    // Send the form data to the server using an HTTP POST request
    this.http.post('http://localhost:8081/file/upload', formData).subscribe(
      (response) => {
        // Handle the server's response
        console.log("File Sucessfully Uploaded");
        this.print();
      },
      (error) => {
        // Handle any errors
        console.error(error);
      }
    );
  }

  getData() {
    return this.http.get('http://localhost:8081/file/list');
}

data: any;

print()
{
  this.getData().subscribe(data => {
    this.data = data;
  });
  
}
delete_id: string | undefined;
file_name: string | undefined;

deletes(id: string,name: string) {
  this.delete_id = id;
  console.log(id);
  this.file_name = name;
  console.log(name);
  
  // window.location.reload();
  
  this.http.get(`http://localhost:8081/file/delete?id=${this.delete_id}`).subscribe(
    (response) => {
      // Handle the server's response
      console.log(response);
      // this.print();

    },
    (error) => {
      // Handle any errors
      console.error(error);
    }
  );

  this.http.get(`http://localhost:8081/file/deletefile?name=${this.file_name}`).subscribe(
    (response) => {
      // Handle the server's response
      console.log(response);
      // this.print();

    },
    (error) => {
      // Handle any errors
      console.error(error);
    }
  );
  this.print();
}

download(name: string) {
  this.http.get(`http://localhost:8081/file/download/${name}`, { responseType: 'blob' }).subscribe(
    (response: Blob) => {
      // Create a URL for the downloaded file
      const url = window.URL.createObjectURL(response);

      // Create a temporary link and trigger the download
      const link = document.createElement('a');
      link.href = url;
      link.download = name;
      link.click();

      // Clean up the temporary URL object
      window.URL.revokeObjectURL(url);
    },
    (error) => {
      // Handle any errors
      console.error(error);
    }
  );
  
  this.print();
}




}
