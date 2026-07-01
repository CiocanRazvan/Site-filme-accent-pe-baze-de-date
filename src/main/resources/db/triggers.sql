CREATE TRIGGER trg_update_rating
AFTER INSERT OR UPDATE OR DELETE ON recenzii
FOR EACH ROW
EXECUTE FUNCTION fn_recalculare_rating();

CREATE TRIGGER trg_curata_date_client
BEFORE INSERT OR UPDATE ON clienti
FOR EACH ROW
EXECUTE FUNCTION fn_valideaza_date_client();