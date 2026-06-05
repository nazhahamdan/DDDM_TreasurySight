import { Component, OnInit } from '@angular/core';
import { PrevesionService } from '../../services/prevesion-service';
import { Navbar } from '../../layout/navbar/navbar';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MacroCard } from '../../layout/macro-card/macro-card';

@Component({
  selector: 'app-prevesion',
  imports: [Navbar,CommonModule, FormsModule,MacroCard],
  templateUrl: './prevesion.html',
  styleUrl: './prevesion.css',
})
export class Prevesion implements OnInit {

  probabilite: number = 0;
  risque: string = '';
  scenarios: any = null;
  loading: boolean = true;
  afficherResultat: boolean = false;

  // ✅ Plus de donnees = {} et variables = []

  constructor(private previsionService: PrevesionService) {}

  // ✅ Chargement automatique
  ngOnInit() {
    this.previsionService.predireRisque().subscribe({
      next: (result) => {
        this.probabilite      = result.probabilite * 100;
        this.risque           = result.risque;
        this.scenarios        = result.scenarios;
        this.loading          = false;
        this.afficherResultat = true;
      },
      error: (err) => {
        console.error('Erreur :', err);
        this.loading = false;
      }
    });
  }

  getRisqueClass(): string {
    if (this.probabilite > 60) return 'risk-high';
    if (this.probabilite > 30) return 'risk-medium';
    return 'risk-low';
  }
}
