import { Component, OnInit } from '@angular/core';
import { CommonModule, DecimalPipe } from '@angular/common';
import { NgxEchartsDirective } from 'ngx-echarts';
import { EChartsOption } from 'echarts';

import { AnalyticsClientService } from '../../services/analytics-client';
import { ClientRisk } from '../../models/client-risk.model';
import { Navbar } from '../../layout/navbar/navbar';

@Component({
  selector: 'app-analytics-clients',
  imports: [CommonModule, DecimalPipe, NgxEchartsDirective, Navbar],
  templateUrl: './analytics-clients.html',
  styleUrls: ['./analytics-clients.css']
})
export class AnalyticsClients implements OnInit {

  clients: ClientRisk[] = [];

  totalCa = 0;
  totalOverdue = 0;
  avgDelay = 0;
  riskyClients = 0;

  // =========================
  // ECHARTS OPTIONS
  // =========================

  scatterChartOption!: EChartsOption;
  paretoChartOption!: EChartsOption;
  distributionChartOption!: EChartsOption;

  constructor(
    private analyticsService: AnalyticsClientService
  ) {}

  ngOnInit(): void {

    const entrepriseId = Number(
      localStorage.getItem('entrepriseId')
    );

    this.analyticsService
      .getAnalysis(1)
      .subscribe(data => {

        this.clients = data;

        this.computeKpis();

        this.buildScatterChart();
        this.buildParetoChart();
        this.buildDistributionChart();
      });
  }

  // =========================
  // KPI
  // =========================
  computeKpis(): void {

    this.totalCa =
      this.clients.reduce((sum, c) => sum + c.totalCa, 0);

    this.totalOverdue =
      this.clients.reduce((sum, c) => sum + c.overdueAmount, 0);

    this.avgDelay =
      this.clients.length
        ? this.clients.reduce((sum, c) => sum + c.averageDelay, 0) / this.clients.length
        : 0;

    this.riskyClients =
      this.clients.filter(c => c.score >= 70).length;
  }

  // =========================
  // SCATTER CA vs RETARD
  // =========================
  private buildScatterChart(): void {

    this.scatterChartOption = {
      color: ['#2563eb'],

      tooltip: {
        trigger: 'item',
        formatter: (params: any) => {
          return `
            <b>${params.data.name}</b><br/>
            CA: ${params.data.value[0]}<br/>
            Retard: ${params.data.value[1]} jours
          `;
        }
      },

      xAxis: {
        name: 'CA'
      },

      yAxis: {
        name: 'Retard moyen (jours)'
      },

      series: [
        {
          type: 'scatter',

          itemStyle: {
            color: '#2563eb'
          },

          symbolSize: (value: any) => {
            const ca = value[0];
            return Math.max(10, Math.min(40, ca / 5000));
          },

          data: this.clients.map(c => ({
            name: c.client,
            value: [
              c.totalCa,
              c.averageDelay
            ]
          }))
        }
      ]
    };
  }

  // =========================
  // PARETO CASH
  // =========================
  private buildParetoChart(): void {

    const sorted = [...this.clients]
      .sort((a, b) => b.totalCa - a.totalCa);

    this.paretoChartOption = {
      color: ['#2563eb'],

      tooltip: {},
      
      xAxis: {
        type: 'category',
        data: sorted.map(c => c.client),
        axisLabel: {
          rotate: 90,
          interval: 0
        }
      },

      yAxis: {
        type: 'value'
      },

      series: [
        {
          type: 'bar',

          itemStyle: {
            color: '#2563eb'
          },

          data: sorted.map(c => c.totalCa)
        }
      ]
    };
  }

  // =========================
  // DISTRIBUTION RETARDS
  // =========================
  private buildDistributionChart(): void {

    let low = 0;
    let medium = 0;
    let high = 0;

    this.clients.forEach(c => {

      if (c.averageDelay <= 5) {
        low++;
      } else if (c.averageDelay <= 15) {
        medium++;
      } else {
        high++;
      }

    });

    this.distributionChartOption = {
      color: ['#2563eb'],

      tooltip: {},

      xAxis: {
        type: 'category',
        data: ['0-5 jours', '5-15 jours', '15+ jours']
      },

      yAxis: {
        type: 'value'
      },

      series: [
        {
          type: 'bar',

          itemStyle: {
            color: '#2563eb'
          },

          data: [low, medium, high]
        }
      ]
    };
  }
}