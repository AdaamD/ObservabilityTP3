package lps;

public class UserProfile {
    private String userId;
    private int createUserCount;
    private int displayProductsCount;
    private int fetchProductCount;
    private int addProductCount;
    private int deleteProductCount;
    private int updateProductCount;

    public UserProfile(String userId) {
        this.userId = userId;
    }

    // Getters
    public String getUserId() { return userId; }
    public int getCreateUserCount() { return createUserCount; }
    public int getDisplayProductsCount() { return displayProductsCount; }
    public int getFetchProductCount() { return fetchProductCount; }
    public int getAddProductCount() { return addProductCount; }
    public int getDeleteProductCount() { return deleteProductCount; }
    public int getUpdateProductCount() { return updateProductCount; }

    // Increment methods
    public void incrementCreateUserCount() { this.createUserCount++; }
    public void incrementDisplayProductsCount() { this.displayProductsCount++; }
    public void incrementFetchProductCount() { this.fetchProductCount++; }
    public void incrementAddProductCount() { this.addProductCount++; }
    public void incrementDeleteProductCount() { this.deleteProductCount++; }
    public void incrementUpdateProductCount() { this.updateProductCount++; }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId='" + userId + '\'' +
                ", createUserCount=" + createUserCount +
                ", displayProductsCount=" + displayProductsCount +
                ", fetchProductCount=" + fetchProductCount +
                ", addProductCount=" + addProductCount +
                ", deleteProductCount=" + deleteProductCount +
                ", updateProductCount=" + updateProductCount +
                '}';
    }
}
