package es.proof.grpc.clients.adapter.persistence;

import org.springframework.data.repository.CrudRepository;

import es.proof.grpc.clients.adapter.persistence.entity.ProductEntity;

public interface ProductEntityRepository extends CrudRepository<ProductEntity, Long> {

	ProductEntity findByProductId(Integer id);
}
