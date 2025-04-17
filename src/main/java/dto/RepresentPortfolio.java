package dto;

public class RepresentPortfolio {
    int portfolio_id;
    int priority;

    public RepresentPortfolio(int portfolio_id, int priority) {
        this.portfolio_id = portfolio_id;
        this.priority = priority;
    }

    public int getPortfolio_id() {
        return portfolio_id;
    }

    public void setPortfolio_id(int portfolio_id) {
        this.portfolio_id = portfolio_id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
