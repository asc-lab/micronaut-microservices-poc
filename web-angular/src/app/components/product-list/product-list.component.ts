import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../shared/product-service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Object[];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getProducts().subscribe(response => {
      //Must be changes for angular version
      for (let product of response)  {
        product.image = product.image.replace('static', 'assets');
      }
      this.products = response;
    })
  }

}
