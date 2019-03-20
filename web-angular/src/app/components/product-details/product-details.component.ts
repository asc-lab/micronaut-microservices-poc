import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../shared/product-service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormlyFieldConfig, FormlyFormOptions} from "@ngx-formly/core";
import {FormGroup} from "@angular/forms";
import {PolicyService} from "../../shared/policy-service";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  form = new FormGroup({});
  options: FormlyFormOptions = {};
  fields: FormlyFieldConfig[];
  model = {
    productCode: '',
    policyFrom: '',
    policyTo: '',
    answers: [],
    selectedCovers: []
  };

  productDetails: any = {};
  proposedOffer = {
    totalPrice: null,
    offerNumber: null
  };

  constructor(private productService: ProductService,
              private policyService: PolicyService,
              private activeRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {

    this.model.productCode = this.activeRoute.snapshot.params.productCode;
    this.productService.getProduct(this.model.productCode).subscribe(response => {
      console.log(response);
      this.productDetails = response;
      this.productDetails.image = this.productDetails.image.replace('static', 'assets');

      if (!this.productDetails.questions)
        return;

      this.addAllCoversToModel();
      this.buildFields();
    });
  }

  private initFieldsConfig(): FormlyFieldConfig[] {
    return [
      {
        key: 'policyFrom',
        type: 'datepicker',
        templateOptions: {
          label: 'Policy from',
          required: true
        }
      },
      {
        key: 'policyTo',
        type: 'datepicker',
        templateOptions: {
          label: 'Policy to',
          required: true
        }
      }
    ];
  }

  private buildFields() {
    let fieldsConfig = this.initFieldsConfig();

    for (let i = 0; i < this.productDetails.questions.length; i++) {
      let question = this.productDetails.questions[i];
      let questionField = {};

      this.model.answers.push({
        questionCode: question.code,
        type: question.type,
        answer: ''
      });
      const key = 'answers[' + [i] + '].answer';
      if (question.type === 'choice') {
        questionField = this.createChoiceField(question, key);
      } else if (question.type === 'numeric') {
        questionField = this.createNumberField(question, key);
      }

      fieldsConfig.push(questionField);
    }

    this.fields = fieldsConfig;
  }

  private addAllCoversToModel() {
    for (let i = 0; i < this.productDetails.covers.length; i++) {
      this.model.selectedCovers.push(this.productDetails.covers[i].code);
    }
  }

  calculatePrice(model) {
    console.log(model);
    if (this.form.valid) {
      this.policyService.calculatePrice(model).subscribe(
        response => {
          this.form.disable();
          this.proposedOffer.offerNumber = response.offerNumber;
          this.proposedOffer.totalPrice = response.totalPrice;
        },
        err => {
          alert('Bad Things Happened!');
        }
      );
    }

  }

  private createNumberField(question, key) {
    return {
      key: key,
      type: 'input',
      templateOptions: {
        label: question.text,
        required: true,
        type: 'number'
      }
    };
  }

  private createChoiceField(question, key) {
    return {
      key: key,
      type: 'select',
      templateOptions: {
        label: question.text,
        required: true,
        options: question.choices.map(function (x) {
          return {value: x.code, label: x.label}
        })
      }
    };
  }

  backToEdit() {
    this.form.enable();
  }

  buyOffer() {
    this.router.navigate(['/policy/fromOffer/', this.proposedOffer.offerNumber]);
  }
}
