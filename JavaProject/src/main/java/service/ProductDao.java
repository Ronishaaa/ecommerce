package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import utils.DatabaseConnectivity;

public class ProductDao {
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private boolean isSuccess;
	private static final String[] errorMessage = new String[2];
	private static final String insert_query = "insert into product"
			+ "(product_name,product_description,unit_price,stock)"
			+ " values(?,?,?,?)";

	public ProductDao() {
		conn = DatabaseConnectivity.getDbConnection();
	}

	public boolean saveProduct(Product product) {
		try {
			
			statement = this.conn.prepareStatement("select count(*) from product");
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				if (check(product)) {
					isSuccess = false;
				} else {
					int row = setData(product);
					if (row > 0) {
						isSuccess = true;
					} else {
						isSuccess = false;
					}
				}

			} else {
				int row = setData(product);
				if (row > 0) {
					isSuccess = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isSuccess;
	}

	public int setData(Product product) {
		int row = 0;
		try {
			statement = conn.prepareStatement(insert_query);
			statement.setString(1, product.getProduct_name());
			statement.setString(2, product.getProduct_description());
			statement.setInt(3, product.getUnit_price());
			statement.setInt(4, product.getStock());
		
			row = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	public boolean check(Product product) {
		boolean isFind = false;
		try {
			statement = conn.prepareStatement("select product_name,product_description from product");
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				if (product.getProduct_name().equals(resultSet.getString("product_name"))) {
					isFind = true;
					break;
				} else if (product.getProduct_description().equals(resultSet.getString("product_description"))) {
					isFind = true;
					break;
		
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isFind;
	}

//	public boolean productLogin(String username, String password) throws SQLException {
//		statement = conn.prepareStatement("select username,password,role_id from product_register where username=?");
//		statement.setString(1, username);
//		resultSet = statement.executeQuery();
//		boolean isSuccess = false;
//		if (resultSet.next()) {
//			String passwordFromDb = resultSet.getString("password");
//
//			if (PasswordHash.verifyPassword(password, passwordFromDb) && resultSet.getInt("role_id")==2) {
//				isSuccess = true;
//			} else {
//				isSuccess = false;
//			}
//
//		}
//		return isSuccess;
//
//	}
	public List<Product> getAllProduct() throws SQLException {
		
		statement=conn.prepareStatement("select * from product");
		resultSet=statement.executeQuery();
		List<Product> listOfProduct=new ArrayList<Product>();
		while(resultSet.next())
		{
			int product_id=resultSet.getInt("product_id");
			String product_name=resultSet.getString("product_name");
			String product_description=resultSet.getString("product_description");
			int unit_price=resultSet.getInt("unit_price");
			int stock=resultSet.getInt("stock");
		
			Product product=new Product();
			product.setProduct_name(product_name);
			product.setProduct_description(product_description);
			product.setStock(stock);
			product.setUnit_price(unit_price);

			
			listOfProduct.add(product);
		}
		return listOfProduct;
	}
	

}
