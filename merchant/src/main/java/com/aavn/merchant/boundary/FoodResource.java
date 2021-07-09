package com.aavn.merchant.boundary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.aavn.merchant.entity.Food;

@Path("/foods")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FoodResource {
	
	Map<String, Set<Food>> foodsFromMerchant;
	
	public FoodResource() {
		foodsFromMerchant = new HashMap<>();
		Set<Food> foods = new HashSet<>();
        foods.add(new Food("f101", "Bánh tráng trộn", 3.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhFjU3SgO47fIRWUwIYXwasvmMidKDrxT3wcPwSCS2aBSDcnFS-f6YUsAKM0Q&usqp=CAc"));
        foods.add(new Food("f102", "T-ra-sua", 5.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3J5JzXcII9y07i99BOrwQQxa15984qRYGFQ&usqp=CAU"));
        foods.add(new Food("f103", "Cháo hàu sữa", 10.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuY3Rd7FE8LxeAx_aNB-8OvWhTeAJd1s9AaQ&usqp=CAU"));
        foods.add(new Food("f104", "Cháo thịt bò", 13.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5vIhoijdA_O4pec1D8uwIgegjPwdq3J_qDw&usqp=CAU"));
        foods.add(new Food("f105", "Cơm tấm", 21.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4uKJ32MRv2QdTyQpMYTkw1nEpYZ6uyKawTQ&usqp=CAU"));
        foods.add(new Food("f106", "Sầu riêng", 15.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUId-mxkuDNY0kEdVEE9Udh5Yj2Re58wxjLg&usqp=CAU"));
        
        foodsFromMerchant.put("m01", foods);
        
        Set<Food> foods2 = new HashSet<>();
        foods2.add(new Food("f201", "Bánh tráng nướng", 2.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSs3LFHVv8MpZ0SBDXCojLqrhjMvyRt7BiLlT7QGmeDJqgJgIDmLg1blwRjTA&usqp=CAc"));
        foods2.add(new Food("f202", "T-ra-sua", 10.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3J5JzXcII9y07i99BOrwQQxa15984qRYGFQ&usqp=CAU"));
        foods2.add(new Food("f203", "Trà chanh", 4.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTo--RTZYCqMUdVG288eX1d9b48kDZdS8cyiw&usqp=CAU"));
        foods2.add(new Food("f204", "Trà đào", 4.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCpRpL7pYpqkFBjD1NWRSeWUvErkaRomx2bZRlfix4AV1sFnH0UQHSN-l0NQ2v2e7R4Zc&usqp=CAU"));
        foods2.add(new Food("f205", "Sinh tố dâu", 5.0, "https://autoshop.com.vn/wp-content/uploads/2020/04/sinh-t%E1%BB%91-d%C3%A2u.jpg"));
        foods2.add(new Food("f206", "Sinh tố mít", 5.0, "https://xukachan.com/wp-content/uploads/2019/06/cach-lam-sinh-to-mit.jpg"));
        
        foodsFromMerchant.put("m02", foods2);
        
        Set<Food> foods3 = new HashSet<>();
        foods3.add(new Food("f301", "Bành mì nướng", 1.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRghF3SxJ0CrdBthat1yP_ZVbGzc5ITPeHFbg&usqp=CAU"));
        foods3.add(new Food("f302", "Sinh tố ổi", 8.0, "https://phunuketnoi.com/wp-content/uploads/2020/04/sinh-to-oi_phunuketnoi.jpg"));
        foods3.add(new Food("f303", "Trà hạt chia", 3.0, "https://trungnguyenecoffee.com/wp-content/uploads/2020/08/Tr%C3%A0-Atis%C3%B4-%C4%90%E1%BB%8F-H%E1%BA%A1t-Chia.jpg"));
        foods3.add(new Food("f304", "Sinh tố bơ", 4.0, "https://cdn.tgdd.vn/Files/2018/05/29/1091772/3-cach-lam-sinh-to-bo--1-760x367.jpg"));
        foods3.add(new Food("f305", "Hồng trà", 6.0, "https://dotea.vn/data/upload/hong-tra.jpg"));
        foods3.add(new Food("f306", "Capuchino", 7.0, "https://90scoffee.vn/wp-content/uploads/2019/05/cafe-ngon-huong-dac-biet.jpg"));
        foods3.add(new Food("f307", "Chè hạt sen", 5.0, "https://cdn.huongnghiepaau.com/wp-content/uploads/2017/08/che-hat-sen-nho-kho.jpg"));
        foodsFromMerchant.put("m03", foods3);
	}

	@GET
	@Path("{merchantId}")
    public Response getFoodsFromMerchant(@PathParam("merchantId") String merchantId) {
		Optional<Set<Food>> foods = foodsFromMerchant.entrySet().stream().filter(entry -> entry.getKey().equals(merchantId)).map(Map.Entry::getValue).findFirst();
		if (foods.isPresent()) {
			return Response.ok(foods.get()).build();
		}
		return Response.status(Status.NOT_FOUND).build();
    }
}
