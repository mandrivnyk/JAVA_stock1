package com.easier.stock;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.List;

public class ProductInStock implements Product {
    private int productID;
    private int categoryID;
    private String urlName;
    private String vendorCode;
    private String titleOne;
    private String titleTwo;
    private String productCode;
    private String name;
    private String NameSupplier;
    private String description;
    private String metaDescription;
    private String metaKeywords;
    private int inStock;
    private int SortOrder;
    private int skidka;
    private int numTopsale;
    private String size;
    private String color;
    private String moreInfo;
    private String barcode;
    private String brend;
    private double priceRRZ;
    private double priceUE;
    private double listPrice;
    private double priceSpec;
    private double priceOpt;
    private int year;
    private int type;
    private int enabled;
    private boolean isNew;


    public static final int TYPE_NEW   = 1;
    public static final int TYPE_PROKAT   = 2;
    public static final int TYPE_BU   = 3;

    Connection conn;

    public ProductInStock() throws SQLException {
        this.conn = ConnectionDB.getInstance().getConn();
        this.setIsNew(false);
    }

    @Override
    public int getNumTopsale() {
        return numTopsale;
    }

    @Override
    public void setNumTopsale(int numTopsale) {
        this.numTopsale = numTopsale;
    }

    @Override
    public int getSkidka() {
        return skidka;
    }

    @Override
    public void setSkidka(int skidka) {
        this.skidka = skidka;
    }

    @Override
    public double getPriceUE() {
        return priceUE;
    }

    @Override
    public void setPriceUE(double priceUE) {
        this.priceUE = priceUE;
    }

    @Override
    public String getNameSupplier() {
        return NameSupplier;
    }

    @Override
    public void setNameSupplier(String nameSupplier) {
        NameSupplier = nameSupplier;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String getUrlName() {
        return urlName;
    }

    @Override
    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    @Override
    public String getMetaDescription() {
        return metaDescription;
    }

    @Override
    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    @Override
    public String getTitleOne() {
        return titleOne;
    }

    @Override
    public void setTitleOne(String titleOne) {
        this.titleOne = titleOne;
    }

    @Override
    public String getTitleTwo() {
        return titleTwo;
    }

    @Override
    public void setTitleTwo(String titleTwo) {
        this.titleTwo = titleTwo;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    public int getEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    @Override
    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public String getProductCode() {
        if (productCode == null) {
            return "";
        }
        return productCode;
    }

    @Override
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getMoreInfo() {
        if (moreInfo == null) {
            return "";
        }
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
    public String getVendorCode() {
        if (vendorCode == null) {
            return "";
        }
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public int getSortOrder() {
        return SortOrder;
    }

    public void setSortOrder(int sortOrder) {
        SortOrder = sortOrder;
    }

    public String getName() {
        if (name == null) {
            return "";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getSize() {
        if (size == null) {
            return "";
        }
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        if (color == null) {
            return "";
        }
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBrend() {
        if (brend == null) {
            return "";
        }
        return brend;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public double getPriceRRZ() {
        return priceRRZ;
    }

    public void setPriceRRZ(double priceRRZ) {
        this.priceRRZ = priceRRZ;
    }

    @Override
    public double getListPrice() {
        return listPrice;
    }

    @Override
    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getPriceSpec() {
        return priceSpec;
    }

    public void setPriceSpec(double priceSpec) {
        this.priceSpec = priceSpec;
    }

    public double getPriceOpt() {
        return priceOpt;
    }

    public void setPriceOpt(double priceOpt) {
        this.priceOpt = priceOpt;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public void getProduct(String productCode) throws SQLException {
        //Statement st = conn.createStatement();
        String queryString ="SELECT * FROM SS_products WHERE product_code = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(queryString);
        preparedStatement.setString(1, productCode);
        ResultSet rs = preparedStatement.executeQuery();

        //ResultSet rs = st.executeQuery("SELECT * FROM SS_products WHERE product_code ='"+productCode+"'");
        if (rs.first()) {
            setProductID(rs.getInt("productId"));
            setProductCode(rs.getString("product_code"));
            setBarcode(rs.getString("barcodes"));
            setBrend(rs.getString("producer"));
            setName(rs.getString("name"));
            setPriceRRZ(rs.getDouble("Price"));
            setInStock(rs.getInt("in_stock"));
            setSortOrder(rs.getInt("sort_order"));
        }
    }

    public void updateProduct() throws SQLException {
        String updateString =
                "UPDATE SS_products " +
                       "set product_code = ?, barcodes = ?, producer = ?, name = ?, Price = ?, list_price = ?, in_stock= ?, sort_order = ?  where productID = ?";
        PreparedStatement updateProductInStock = conn.prepareStatement(updateString);
        updateProductInStock.setString(1,  getProductCode());
        updateProductInStock.setString(2,  getBarcode());
        updateProductInStock.setString(3,  getBrend());
        updateProductInStock.setString(4,  getName());
        updateProductInStock.setDouble(5,  getPriceRRZ());
        updateProductInStock.setDouble(6,  getListPrice());
        updateProductInStock.setInt(7,  getInStock());
        updateProductInStock.setInt(8, getSortOrder());
        updateProductInStock.setInt(9, getProductID());

        updateProductInStock.executeUpdate();
    }

//    INSERT INTO `prokat`.`SS_products` (`productID`, `categoryID`, `title_one`, `title_two`, `url_name`, `name`, `name_supplier`,
//            `nomentklatura`, `producer`, `description`, `customers_rating`, `Price`, `Price_UE`, `in_stock`, `skidka`, `customer_votes`,
//            `items_sold`, `enabled`, `brief_description`, `description2`, `brief_description2`, `list_price`, `product_code`, `sort_order`,
//            `default_picture`, `num_topsale`, `date_added`, `date_modified`, `viewed_times`, `eproduct_filename`, `eproduct_available_days`,
//            `eproduct_download_times`, `weight`, `meta_description`, `meta_keywords`, `free_shipping`, `min_order_amount`, `shipping_freight`,
//            `classID`, `barcodes`, `type`) VALUES (NULL, NULL, '', '', '', 'test', '', '', 'test', 'test', '0', '234234', '', '50', '', '0', '0',
//                                                   NULL, NULL, NULL, NULL, NULL, '234234', '0', NULL, '', NULL, NULL, '0', NULL, '5', '5', '0', NULL,
//                                                   NULL, '0', '1', '0', NULL, '0', '1');
    public void add() throws SQLException {
        String insertString =
                "INSERT INTO SS_products " +
                       "(categoryID, title_one, title_two, url_name, name, name_supplier, producer, description, Price, in_stock," +
                        " enabled, list_price, product_code, sort_order, " +
                        " meta_description, meta_keywords, " +
                        "barcodes, type, Price_UE, skidka, num_topsale)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement insertProductInStock = conn.prepareStatement(insertString);
        insertProductInStock.setInt(1,  getCategoryID());
        insertProductInStock.setString(2, getTitleOne()  );
        insertProductInStock.setString(3, getTitleTwo() );
        insertProductInStock.setString(4,  getUrlName());
        insertProductInStock.setString(5,  getName());
        insertProductInStock.setString(6,  getNameSupplier());
        insertProductInStock.setString(7,  getBrend());
        insertProductInStock.setString(8,  getDescription());
        insertProductInStock.setDouble(9,  getPriceRRZ());
        insertProductInStock.setInt(10,  getInStock());
        insertProductInStock.setInt(11,  getEnabled());
        insertProductInStock.setDouble(12,  getListPrice());
        insertProductInStock.setString(13,  getProductCode());
        insertProductInStock.setInt(14, getSortOrder());
        insertProductInStock.setString(15,  getMetaDescription());
        insertProductInStock.setString(16,  getMetaKeywords());
        insertProductInStock.setString(17,  getBarcode());
        insertProductInStock.setInt(18,  getType());
        insertProductInStock.setDouble(19,  getPriceUE());
        insertProductInStock.setInt(20,  getSkidka());
        insertProductInStock.setInt(21,  getNumTopsale());

        insertProductInStock.executeUpdate();
    }

    public static String transliterate(String message){
        char[] abcCyr =   {' ','а','б','в','г','д','е','ё', 'ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х', 'ц','ч', 'ш','щ','ъ','ы','ь','э', 'ю','я','А','Б','В','Г','Д','Е','Ё', 'Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х', 'Ц', 'Ч','Ш', 'Щ','Ъ','Ы','Ь','Э','Ю','Я','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String[] abcLat = {" ","a","b","v","g","d","e","e","zh","z","i","y","k","l","m","n","o","p","r","s","t","u","f","h","ts","ch","sh","sch", "","i", "","e","ju","ja","A","B","V","G","D","E","E","Zh","Z","I","Y","K","L","M","N","O","P","R","S","T","U","F","H","Ts","Ch","Sh","Sch", "","I", "","E","Ju","Ja","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            for (int x = 0; x < abcCyr.length; x++ ) {
                if (message.charAt(i) == abcCyr[x]) {
                    builder.append(abcLat[x]);
                }
            }
        }
        return builder.toString();
    }

    public void cleanNameFromBrend() {
        String newName = getName().replaceFirst(getBrend(), "");
        setName(newName);
    }

    public String createUrlName() {
        String urlNamePart = getName().toLowerCase().replaceFirst(getBrend().toLowerCase(), "");
        String url = getBrend() + urlNamePart;
        return transliterate(url).replaceAll(" ", "-").toLowerCase();
    }

    public String createMetaDescription(){
        String metaDescription = getBrend();
        metaDescription += " "+getName().toLowerCase().replaceFirst(getBrend().toLowerCase(), "");
        metaDescription += " "+getDescription();
        if(metaDescription.length() >=300) {
            metaDescription = metaDescription.substring(0, 300);
        }
        return metaDescription;

    }

    public String createMetaKeywords(){
        String metaKeywords = getBrend();
        metaKeywords += " "+getName().toLowerCase().replaceFirst(getBrend().toLowerCase(), "");
        metaKeywords += " купить, Киев, Украина";
        metaKeywords = metaKeywords.trim().replaceAll("( )+", " ");
        metaKeywords = metaKeywords.replaceAll(" ", ", ");
        if(metaKeywords.length() >=255) {
            metaKeywords = metaKeywords.substring(0, 255);
        }
        return metaKeywords;

    }

    public void checkIfNewProduct(){
        if( getProductCode() == null || getProductCode().isEmpty()) {
            setIsNew(true);
        }
    }

    public static void setInStockAll(int inStock, int sortOrder, String nameSupplier) throws SQLException {
        Connection conn = ConnectionDB.getInstance().getConn();
        CreatorSupplier creatorSupplier = new CreatorSupplier();
        iSupplier supplier = creatorSupplier.create(nameSupplier);
        List<String> brends = supplier.getSupplierBrends();

        for (String brend: brends) {
            String updateString =
                    "UPDATE SS_products " +
                            "set in_stock= ?, sort_order = ?  where producer = ? AND type= ?";
            PreparedStatement updateProductInStock = conn.prepareStatement(updateString);
            updateProductInStock.setInt(1,  inStock);
            updateProductInStock.setInt(2,  sortOrder);
            updateProductInStock.setString(3,  brend);
            updateProductInStock.setInt(4,  TYPE_NEW);
            updateProductInStock.executeUpdate();
        }
        

    }

    public void addBarcodeIntoProduct(String barcode) throws SQLException {
        if (getBarcode() != null) {
            setBarcode(barcode+"\r\n"+getBarcode());
        }
        else {
            setBarcode(barcode);
        }
    }
}
