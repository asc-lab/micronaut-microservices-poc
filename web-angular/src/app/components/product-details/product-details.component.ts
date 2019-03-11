import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../shared/product-service";
import {ActivatedRoute} from "@angular/router";
import {FormlyFieldConfig, FormlyFormOptions} from "@ngx-formly/core";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  form = new FormGroup({});
  options: FormlyFormOptions = {};
  model = {
    productCode: '',
    policyFrom: '',
    policyTo: '',
    selectedCovers: [],
    answers: []
  };
  fields: FormlyFieldConfig[];

  productDetails: any = {};
  mode: string = 'EDIT';
  price = {
    amountToPay: null
  };

  constructor(private productService: ProductService,
              private activeRoute: ActivatedRoute) {
  }

  ngOnInit() {
    let fieldsConfig = [
      {
        key: 'policyFrom',
        type: 'datepicker',
        templateOptions: {
          label: 'Policy from',
          required: true
        }
      },
      {
        key: 'policyFrom',
        type: 'datepicker',
        templateOptions: {
          label: 'Policy to',
          required: true
        }
      }
    ];
    const productCode = this.activeRoute.snapshot.params.productCode;
    this.productService.getProduct(productCode).subscribe(response => {
      console.log(response);

      this.productDetails = response;
      this.productDetails.image = this.productDetails.image.replace('static', 'assets');
      if (!this.productDetails.questions)
        return;

      for (let i = 0; i < this.productDetails.questions.length; i++) {
        let question = this.productDetails.questions[i];
        let questionField = this.createEmptyField();

        if (question.type === 'choice') {
          questionField = this.createChoiceField(questionField, question);
        } else if (question.type === 'numeric') {
          questionField = this.createNumberField(questionField, question);
        }

        fieldsConfig.push(questionField);
      }
      this.fields = fieldsConfig;
    });
  }

  private createEmptyField() {
    return {
      key: '',
      type: '',
      templateOptions: {
        label: '',
        required: true
      }
    };
  }

  private createNumberField(questionField, question) {
    questionField = {
      key: question.code,
      type: 'input',
      templateOptions: {
        label: question.text,
        required: true,
        type: 'number'
      }
    };
    return questionField;
  }

  private createChoiceField(questionField, question) {
    questionField = {
      key: question.code,
      type: 'select',
      templateOptions: {
        label: question.text,
        required: true,
        options: question.choices
      },
      valueProp: 'code',
      labelProp: 'label',
    };
    return questionField;
  }

  backToEdit() {
    this.mode = 'EDIT';
  }
}
