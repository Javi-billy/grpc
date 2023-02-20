package es.proof.grpc.servers.adapter.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.proof.grpc.servers.adapter.persistence.entity.PriceEntity;

public interface PriceEntityRepository extends CrudRepository<PriceEntity, Long> {

	List<PriceEntity> findByProductIdAndBrandIdOrderByPriorityDesc(Integer id, String brandId);
	
	PriceEntity findByProductId(Integer id);

}
