import { Component } from '@angular/core';
import { PrevesionService } from '../../services/prevesion-service';
import { Navbar } from '../../layout/navbar/navbar';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-prevesion',
  imports: [Navbar,CommonModule, FormsModule],
  templateUrl: './prevesion.html',
  styleUrl: './prevesion.css',
})
export class Prevesion {
  donnees: any = {};
  probabilite: number = 0;
  risque: string = '';
  scenarios: any = null;
  loading: boolean = false;
  afficherResultat: boolean = false;

  // Labels des variables (adapter selon votre dataset)
  variables = [
    { key: 'X1',  label: 'Ratio de liquidité générale' },
    { key: 'X2',  label: 'Ratio d\'endettement' },
    { key: 'X3',  label: 'Rentabilité des actifs (ROA)' },
    { key: 'X4',  label: 'Cash-flow opérationnel' },
    { key: 'X5',  label: 'Ratio de solvabilité' },
    { key: 'X6',  label: 'Marge bénéficiaire nette' },
    { key: 'X7',  label: 'Rotation des actifs' },
    { key: 'X8',  label: 'Ratio de couverture des intérêts' },
    { key: 'X9',  label: 'Fonds de roulement / Total actifs' },
    { key: 'X10', label: 'Bénéfices non distribués / Total actifs' }
  ];

  constructor(private previsionService: PrevesionService) {
    // Initialiser toutes les valeurs à 0
    this.variables.forEach(v => this.donnees[v.key] = 0);
  }

  analyser() {
    this.loading = true;
    this.afficherResultat = false;

    this.previsionService.predireRisque(this.donnees).subscribe({
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

  reset() {
    this.variables.forEach(v => this.donnees[v.key] = 0);
    this.afficherResultat = false;
    this.probabilite = 0;
  }

}
