package es.proof.grpc.clients.adapter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import es.proof.grpc.clients.adapter.persistence.entity.ProductEntity;
import es.proof.grpc.clients.domain.model.Product;

@Mapper(componentModel = "spring",
nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductMapper {

	ProductEntity mapToProductEntity(Product p);

	Product mapToProduct(ProductEntity p);
}
