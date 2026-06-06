
INSERT INTO evenements
(description, montant, type_operation, date_echeance, sous_categorie, type, statut, est_recurrent, id_entreprise)
VALUES

-- =======================
-- JUILLET 2026
-- =======================
('Loyer juillet', 12000, 'DEBIT', '2026-07-05', 'LOYER', 'CHARGE_RECURRENTE', 'PREVU', true, 1),
('Salaires juillet', 28000, 'DEBIT', '2026-07-31', 'SALAIRES', 'CHARGE_RECURRENTE', 'PREVU', true, 1),
('Facture client Orange Maroc', 34000, 'CREDIT', '2026-07-15', 'FACTURE_CLIENT', 'CREANCE_CLIENT', 'PREVU', false, 1),
('TVA T2 2026 à reverser à la DGI', 16500, 'DEBIT', '2026-07-20', 'TVA', 'DETTE_FOURNISSEUR', 'PREVU', false, 1),

-- =======================
-- AOUT 2026
-- =======================
('Loyer août', 12000, 'DEBIT', '2026-08-05', 'LOYER', 'CHARGE_RECURRENTE', 'PREVU', true, 1),
('Salaires août', 28000, 'DEBIT', '2026-08-31', 'SALAIRES', 'CHARGE_RECURRENTE', 'PREVU', true, 1),
('Facture client OCP extension', 72000, 'CREDIT', '2026-08-18', 'FACTURE_CLIENT', 'CREANCE_CLIENT', 'PREVU', false, 1),

-- =======================
-- SEPTEMBRE 2026
-- =======================
('Loyer septembre', 12000, 'DEBIT', '2026-09-05', 'LOYER', 'CHARGE_RECURRENTE', 'PREVU', true, 1),
('Salaires septembre', 28000, 'DEBIT', '2026-09-30', 'SALAIRES', 'CHARGE_RECURRENTE', 'PREVU', true, 1),
('Facture client Bank Africa', 78000, 'CREDIT', '2026-09-12', 'FACTURE_CLIENT', 'CREANCE_CLIENT', 'PREVU', false, 1),
('TVA T3 2026 estimée', 17800, 'DEBIT', '2026-09-20', 'TVA', 'DETTE_FOURNISSEUR', 'PREVU', false, 1);-- 