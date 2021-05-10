import { Component, OnInit } from '@angular/core';
import {ProduitService} from '../Services/produit.service';
import {Produit} from '../produit';
import {HttpErrorResponse} from '@angular/common/http';



@Component({
  selector: 'app-produits',
  templateUrl: './produits.component.html',
  styleUrls: ['./produits.component.css']
})
export class ProduitsComponent implements OnInit {

 public produits: Produit[];

  constructor(private produitService: ProduitService ) {
    this.produits = [];
  }

  ngOnInit(){
    this.getProducts();
  }
  public getProducts(): void{
    this.produitService.getProducts().subscribe(
      (response: Produit[]) => {
        console.log(response);
        this.produits = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
