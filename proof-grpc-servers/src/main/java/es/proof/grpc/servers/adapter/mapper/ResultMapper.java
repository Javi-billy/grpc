package es.proof.grpc.servers.adapter.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

import com.google.protobuf.Timestamp;
import com.grpc.price.PriceResponse;
import es.proof.grpc.servers.adapter.persistence.entity.PriceEntity;
import es.proof.grpc.servers.domain.model.Price;
import es.proof.grpc.servers.domain.model.PriceRequest;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ResultMapper {

	Price mapToPrice(PriceEntity price);
	
	//@Mapping(source = "startDate", target = "startDate", qualifiedByName = "convertDates")
	PriceResponse mapToPriceResponse(PriceEntity price);
	
	//@Mapping(source = "startDate", target = "startDate", qualifiedByName = "convertDates")
	PriceResponse mapToPriceResponse(Price price);
	
	List<PriceResponse> mapToListPriceRsp(List<Price> price);
	
	List<PriceResponse> mapToListPriceResponse(List<PriceEntity> price);
	
	PriceEntity mapToPriceEntity(PriceRequest price);

	
	/*default OffsetDateTime convertDates(LocalDateTime dateTime) {		
		ZoneOffset offset = ZoneOffset.UTC;
		return OffsetDateTime.of(dateTime, offset);
	}*/
	
	@Named("convertDates")
	default Timestamp toTimestamp(LocalDateTime localDateTime) {
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		return Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build();
	}
}
