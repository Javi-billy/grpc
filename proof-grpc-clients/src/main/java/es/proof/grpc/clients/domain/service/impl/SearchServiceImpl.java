package es.proof.grpc.clients.domain.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.grpc.price.Empty;
import com.grpc.price.Id;
import com.grpc.price.PriceGrpcSrvGrpc.PriceGrpcSrvBlockingStub;
import com.grpc.price.PriceGrpcSrvGrpc.PriceGrpcSrvStub;
import com.grpc.price.PriceResponse;

import es.proof.grpc.clients.domain.model.CompleteInfo;
import es.proof.grpc.clients.domain.model.PriceGrpc;
import es.proof.grpc.clients.domain.model.Product;
import es.proof.grpc.clients.domain.repository.ProductRespository;
import es.proof.grpc.clients.domain.service.SearchService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class SearchServiceImpl implements SearchService {

	@GrpcClient("local-grpc-server")
	private PriceGrpcSrvBlockingStub syncchronousClient;

	@GrpcClient("local-grpc-server")
	private PriceGrpcSrvStub asyncchronousClient;
		
	private ProductRespository<Product> repository;

	public SearchServiceImpl(final ProductRespository<Product> repository) {
		this.repository = repository;
	}

	@Override
	@CircuitBreaker(name = "pricesMethods", fallbackMethod = "getPriceDefault")
	public Map<Descriptors.FieldDescriptor, Object> getPrice(Integer id) {
		PriceResponse response = syncchronousClient.getPriceId(Id.newBuilder().setId(id).build());
		return response.getAllFields();
	}

	@Override
	@CircuitBreaker(name = "pricesMethods", fallbackMethod = "getPriceV2Default")
	public PriceGrpc getPriceV2(Integer id) {
		PriceResponse response = syncchronousClient.getPriceId(Id.newBuilder().setId(id).build());

		PriceGrpc price = new PriceGrpc();
		price.setBrandId(response.getBrandId());
		price.setPriceList(response.getPriceList());
		price.setPriceProduct(response.getPriceProduct());
		price.setProductId(response.getProductId());
		//price.setStartDate(Instant.ofEpochSecond(response.getStartDate().getSeconds()).atZone(ZoneId.systemDefault()).toLocalDateTime());
		return price;
	}

	@Override
	@CircuitBreaker(name = "pricesMethods", fallbackMethod = "getPriceAsync2Default")
	public Map<String, Map<Descriptors.FieldDescriptor, Object>> getPriceAsync(Integer id) {// throws InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		final Map<String, Map<Descriptors.FieldDescriptor, Object>> response = new HashMap<>();
		asyncchronousClient.getPriceId(Id.newBuilder().setId(id).build(), new StreamObserver<PriceResponse>() {

			@Override
			public void onNext(PriceResponse value) {
				response.put("element", value.getAllFields());
			}

			@Override
			public void onError(Throwable t) {
				countDownLatch.countDown();
			}

			@Override
			public void onCompleted() {
				countDownLatch.countDown();
			}
		});

		boolean await = false;
		try {
			await = countDownLatch.await(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return await ? response : Collections.emptyMap();
	}

	@Override
	public List<Product> getProducts() {
		return repository.getAllProducts();
	}

	@Override
	public List<CompleteInfo> getProductsPrices() {
		List<Product> products = getProducts();
		return products.stream()
				//.map(p -> CompleteInfo.builder().productId(p.getProductId()).createAt(p.getCreateAt()).updateAt(p.getUpdateAt()).name(p.getName()).price(getPriceV2(p.getProductId())).build())
				.map(p -> CompleteInfo.builder().productId(p.getProductId())
						.name(p.getName()).price(getPriceV2(p.getProductId())).build())
				.toList();
	}

	@Override
	public Product getProductId(Integer id) {		
		return repository.getProductId(id);
	}

	@Override
	public CompleteInfo getProductsPrices(Integer id) {
		Product p = getProductId(id);
		//return CompleteInfo.builder().productId(p.getProductId()).createAt(p.getCreateAt()).updateAt(p.getUpdateAt()).name(p.getName()).price(getPriceV2(p.getProductId())).build();		
		return CompleteInfo.builder().productId(p.getProductId()).name(p.getName()).price(getPriceV2(p.getProductId())).build();
	}

	@Override
	public List<Map<FieldDescriptor, Object>> getAllPrices() throws InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(1);		
		final List<Map<Descriptors.FieldDescriptor, Object>> response = new ArrayList<>();	
		asyncchronousClient.getAllStreamPrice(Empty.getDefaultInstance(), new StreamObserver<PriceResponse>() {

			@Override
			public void onNext(PriceResponse value) {
				response.add(value.getAllFields());
			}

			@Override
			public void onError(Throwable t) {
				countDownLatch.countDown();				
			}

			@Override
			public void onCompleted() {
				countDownLatch.countDown();				
			}
			
		});
		boolean await = countDownLatch.await(1, TimeUnit.MINUTES);
		return await ? response : Collections.emptyList();
	}

	 private static Map<Descriptors.FieldDescriptor, Object> getPriceDefault (final Integer id, RuntimeException e) {
		 PriceResponse price = PriceResponse.newBuilder().setBrandId("X").setProductId(id).build();
		 return price.getAllFields();
	 }
	 
	 private static PriceGrpc getPriceV2Default (final Integer id, RuntimeException e) {		 
		 return new PriceGrpc("X",null,id,null);		 
	 }
	 
	 private static Map<String, Map<Descriptors.FieldDescriptor, Object>> getPriceAsync2Default (final Integer id, RuntimeException e) {
		 final Map<String, Map<Descriptors.FieldDescriptor, Object>> response = new HashMap<>();
		 new StreamObserver<PriceResponse>() {

			@Override
			public void onNext(PriceResponse value) {
				response.put("element", PriceResponse.newBuilder().setBrandId("X").setProductId(id).build().getAllFields());				
			}

			@Override
			public void onError(Throwable t) {				
			}

			@Override
			public void onCompleted() {
			}
			 
		}; 
			 
		 
		 return response;
	 }	 
}
