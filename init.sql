INSERT INTO comptes_bancaires (libelle, banque, rib, solde_initial, actif, id_entreprise) VALUES('Compte principal', 'Attijariwafa Bank', 'MA123456789000', 15000.00, true, 1);



INSERT INTO transactions
(description, montant, type_operation, date_transaction, date_paiement, sous_categorie, source, statut, categorise_auto, id_entreprise, id_compte, client)
VALUES
-- JANVIER
('Virement client Maroc Telecom', 45000, 'CREDIT', '2026-01-10', '2026-01-12', 'FACTURE_CLIENT', 'MANUEL', 'REALISE', false, 1, 1, 'Maroc Telecom'),
('TVA collectée facture Maroc Telecom', 9000, 'CREDIT', '2026-01-10', NULL, 'TVA_RECUPERABLE', 'MANUEL', 'REALISE', false, 1, 1, NULL),
('Loyer bureau Maarif', 12000, 'DEBIT', '2026-01-05', NULL, 'LOYER', 'MANUEL', 'REALISE', false, 1, 1, NULL),
('TVA déductible achat équipements', 3200, 'DEBIT', '2026-01-15', NULL, 'TVA_DEDUCTIBLE', 'MANUEL', 'REALISE', false, 1, 1, NULL),
('Salaires janvier', 28000, 'DEBIT', '2026-01-31', NULL, 'SALAIRES', 'MANUEL', 'REALISE', false, 1, 1, NULL),

-- FEVRIER
('Facture client BMCE', 32000, 'CREDIT', '2026-02-15', '2026-02-20', 'FACTURE_CLIENT', 'MANUEL', 'REALISE', false, 1, 1, 'BMCE Bank'),
('TVA collectée facture OCP', 6400, 'CREDIT', '2026-02-15', NULL, 'TVA_RECUPERABLE', 'MANUEL', 'REALISE', false, 1, 1, NULL),
('Télécoms Maroc Telecom', 1500, 'DEBIT', '2026-02-10', NULL, 'TELECOMS', 'MANUEL', 'REALISE', false, 1, 1, NULL),
('TVA déductible prestataire IT', 1800, 'DEBIT', '2026-02-20', NULL, 'TVA_DEDUCTIBLE', 'MANUEL', 'REALISE', false, 1, 1, NULL),
('Salaires février', 28000, 'DEBIT', '2026-02-28', NULL, 'SALAIRES', 'MANUEL', 'REALISE', false, 1, 1, NULL),

-- MARS
('Virement client OCP', 60000, 'CREDIT', '2026-03-05', '2026-03-07', 'FACTURE_CLIENT', 'MANUEL', 'REALISE', false, 1, 1, 'OCP Group'),
('TVA collectée facture Atlas Corp', 11000, 'CREDIT', '2026-03-05', NULL, 'TVA_RECUPERABLE', 'MANUEL', 'REALISE', false, 1, 1, NULL),
('Loyer bureau Maarif', 12000, 'DEBIT', '2026-03-05', NULL, 'LOYER', 'MANUEL', 'REALISE', false, 1, 1, NULL),
('Facture client LabelVie', 28000, 'CREDIT', '2026-03-12', '2026-03-15', 'FACTURE_CLIENT', 'MANUEL', 'REALISE', false, 1, 1, 'LabelVie'),
('Salaires mars', 28000, 'DEBIT', '2026-03-31', NULL, 'SALAIRES', 'MANUEL', 'REALISE', false, 1, 1, NULL),

-- AVRIL
('Virement client Inwi', 40000, 'CREDIT', '2026-04-14', '2026-04-16', 'FACTURE_CLIENT', 'MANUEL', 'REALISE', false, 1, 1, 'Inwi'),
('TVA collectée facture Inwi', 7800, 'CREDIT', '2026-04-12', NULL, 'TVA_RECUPERABLE', 'MANUEL', 'REALISE', false, 1, 1, NULL),
('Salaires avril', 28000, 'DEBIT', '2026-04-30', NULL, 'SALAIRES', 'MANUEL', 'REALISE', false, 1, 1, NULL),

-- MAI
('Facture client CIH Bank', 42000, 'CREDIT', '2026-05-06', '2026-05-10', 'FACTURE_CLIENT', 'MANUEL', 'REALISE', false, 1, 1, 'CIH Bank'),
('Paiement client Orange Business', 11000, 'CREDIT', '2026-05-14', '2026-05-18', 'FACTURE_CLIENT', 'MANUEL', 'REALISE', false, 1, 1, 'Orange Business'),
('Virement client Atlas Corp', 58000, 'CREDIT', '2026-05-16', '2026-05-20', 'FACTURE_CLIENT', 'MANUEL', 'REALISE', false, 1, 1, 'Atlas Corp'),
('Salaires mai', 28000, 'DEBIT', '2026-05-31', NULL, 'SALAIRES', 'MANUEL', 'REALISE', false, 1, 1, NULL),

-- JUIN
('Virement client Bank Partner', 61000, 'CREDIT', '2026-06-10', '2026-06-12', 'FACTURE_CLIENT', 'MANUEL', 'REALISE', false, 1, 1, 'Bank Partner'),
('Salaires juin', 28000, 'DEBIT', '2026-06-30', NULL, 'SALAIRES', 'MANUEL', 'REALISE', false, 1, 1, NULL);




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