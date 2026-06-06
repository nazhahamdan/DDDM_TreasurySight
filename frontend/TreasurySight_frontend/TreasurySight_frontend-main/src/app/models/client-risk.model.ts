export interface ClientRisk {
  client: string;
  totalCa: number;
  overdueAmount: number;
  averageDelay: number;
  invoiceCount: number;
  lateInvoiceCount: number;
  score: number;
}