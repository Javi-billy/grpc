package es.proof.grpc.servers.domain.service.grpc;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.google.protobuf.Timestamp;
import com.grpc.price.Empty;
import com.grpc.price.Id;
import com.grpc.price.PriceGrpcSrvGrpc.PriceGrpcSrvImplBase;
import com.grpc.price.PriceList;
import com.grpc.price.PriceResponse;
import es.proof.grpc.servers.adapter.mapper.ResultMapper;
import es.proof.grpc.servers.domain.model.Price;
import es.proof.grpc.servers.domain.service.SearchService;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class PriceGrpcService extends PriceGrpcSrvImplBase {

	private SearchService<Price> service;
	
	private ResultMapper mapper;

	public PriceGrpcService(final SearchService<Price> service, final ResultMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@Override
	public void getPriceId(Id id, StreamObserver<PriceResponse> responseObserver) {

		System.out.println("************ PETICION RECIBIDA DESDE EL CLIENTE :\n" + id);

		Price price = service.getPriceById(id.getId());

		var response = PriceResponse.newBuilder()
				.setBrandId(price.getBrandId())
				.setPriceList(price.getPriceList())
				.setPriceProduct(price.getPriceProduct())
				.setProductId(price.getProductId())
				//.setStartDate(toTimestamp(price.getStartDate()))
				.build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void getAllPrice(Empty request, StreamObserver<PriceList> responseObserver) {
		//PENDIENTE ********************
		
		super.getAllPrice(request, responseObserver);
	}

	@Override
	public void getAllStreamPrice(Empty request, StreamObserver<PriceResponse> responseObserver) {
		
		var listPrices = mapper.mapToListPriceRsp(service.getAllPrice());		
		listPrices.stream().forEach(responseObserver::onNext);
		responseObserver.onCompleted();						
	}

	protected Timestamp toTimestamp(LocalDateTime localDateTime) {
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		return Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build();
	}

}
