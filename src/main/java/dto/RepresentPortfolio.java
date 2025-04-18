package dto;

public class RepresentPortfolio {
    int portfolioId;
    int priority;

    public RepresentPortfolio(int portfolioId, int priority) {
        this.portfolioId = portfolioId;
        this.priority = priority;
    }

    public int getPortfolioId() {
        return portfolioId;
    }

    public void setportfolioId(int portfolio_id) {
        this.portfolioId = portfolio_id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
