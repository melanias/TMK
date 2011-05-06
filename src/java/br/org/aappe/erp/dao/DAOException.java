package br.org.aappe.erp.dao;

/**
 * @author Phelipe Melanias
 */
public class DAOException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(Throwable e) {
        super(e);
    }

    public DAOException(String msg, Throwable e) {
        super(msg, e);
    }
}