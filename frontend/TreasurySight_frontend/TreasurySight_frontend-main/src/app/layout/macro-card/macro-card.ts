import { Component } from '@angular/core';
import { MacroService } from '../../services/macro-service';
import { CommonModule, DatePipe } from '@angular/common';

@Component({
  selector: 'app-macro-card',
  imports: [CommonModule,DatePipe],
  templateUrl: './macro-card.html',
  styleUrl: './macro-card.css',
})
export class MacroCard {
  indicateurs: any[] = [];
  loading = true;
  derniereMaj: Date = new Date();

  // Icône par indicateur
  icones: { [key: string]: string } = {
    'Unemployment Rate'         : '👷',
    'Consumer Confidence Index' : '😊',
    'PPI Construction Materials': '🏗️',
    'CPI All Items'             : '🛒',
    'Inflation'                 : '📊',
    'Mortgage Interest Rate'    : '🏠',
    'Median Household Income'   : '💰',
    'Corp. Bond Yield'          : '📈',
    'Monthly Home Supply'       : '🏘️',
    'Working Population Share'  : '👥',
    'GDP Per Capita'            : '💵',
    'Quarterly Real GDP'        : '🏦',
    'GDP Growth Rate'           : '📉',
    'Home Price Index (Case-Shiller)': '🏡'
  };

  constructor(private macroService: MacroService) {}

  ngOnInit() {
    this.chargerIndicateurs();
  }

  chargerIndicateurs() {
    this.loading = true;
    this.macroService.getIndicateurs().subscribe({
      next: (data) => {
        this.indicateurs = data;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.loading = false;
      }
    });
  }

  getIcone(nom: string): string {
    return this.icones[nom] || '📊';
  }

  // Indicateurs où la hausse est mauvaise
  // (ex: chômage qui monte = mauvais)
  isHausseNegative(nom: string): boolean {
    const negatifs = [
      'Unemployment Rate',
      'Inflation',
      'Mortgage Interest Rate',
      'Corp. Bond Yield',
      'Monthly Home Supply',
      'PPI Construction Materials',
      'CPI All Items'
    ];
    return negatifs.includes(nom);
  }

  // Couleur selon contexte
  isBon(ind: any): boolean {
    if (this.isHausseNegative(ind.nom)) {
      return !ind.hausse; // baisse = bon
    }
    return ind.hausse; // hausse = bon
  }

}
