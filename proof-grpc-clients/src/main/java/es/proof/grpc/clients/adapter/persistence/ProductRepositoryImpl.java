package es.proof.grpc.clients.adapter.persistence;

import static java.util.stream.StreamSupport.stream;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.proof.grpc.clients.adapter.mapper.ProductMapper;
import es.proof.grpc.clients.adapter.persistence.entity.ProductEntity;
import es.proof.grpc.clients.domain.model.Product;
import es.proof.grpc.clients.domain.repository.ProductRespository;

@Repository
public class ProductRepositoryImpl implements ProductRespository<Product> {

	private ProductEntityRepository productCrudRepository;

	private ProductMapper mapper;

	public ProductRepositoryImpl(final ProductEntityRepository productCrudRepository, final ProductMapper mapper) {
		this.productCrudRepository = productCrudRepository;
		this.mapper = mapper;
	}

	 @Override
	 public List<Product> getAllProducts() {	
		 return getCollect(productCrudRepository.findAll());
	 }

	 private List<Product> getCollect(final Iterable<ProductEntity> productIter) {
		 return stream(productIter
				 .spliterator(), false)
				 .map(p -> mapper.mapToProduct(p)).toList();				 
	 }

	@Override
	public Product getProductId(Integer id) {		
		return mapper.mapToProduct(productCrudRepository.findByProductId(id));		
	}
}
