package es.proof.grpc.clients.domain.service;

import java.util.List;
import java.util.Map;

import com.google.protobuf.Descriptors;

import es.proof.grpc.clients.domain.model.CompleteInfo;
import es.proof.grpc.clients.domain.model.PriceGrpc;
import es.proof.grpc.clients.domain.model.Product;

public interface SearchService {

	Map<Descriptors.FieldDescriptor, Object> getPrice(Integer id);
	
	Map<String, Map<Descriptors.FieldDescriptor, Object>> getPriceAsync(Integer id); // throws InterruptedException;
	
	PriceGrpc getPriceV2(Integer id);
	
	List<Product> getProducts();
	
	List<CompleteInfo> getProductsPrices();
	
	Product getProductId(Integer id);
	
	CompleteInfo getProductsPrices(Integer id);
	
	List<Map<Descriptors.FieldDescriptor, Object>> getAllPrices() throws InterruptedException;		

}
