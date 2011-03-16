package br.org.aappe.erp.dao;

/**
 * @author Phelipe Melanias
 */
public class DaoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DaoException(String msg) {
        super(msg);
    }

    public DaoException(Throwable e) {
        super(e);
    }

    public DaoException(String msg, Throwable e) {
        super(msg, e);
    }
}