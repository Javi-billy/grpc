package es.proof.grpc.clients.adapter.mapper;

import es.proof.grpc.clients.adapter.persistence.entity.ProductEntity;
import es.proof.grpc.clients.domain.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-17T13:25:01+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductEntity mapToProductEntity(Product p) {
        if ( p == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        if ( p.getProductId() != null ) {
            productEntity.setProductId( p.getProductId() );
        }
        if ( p.getName() != null ) {
            productEntity.setName( p.getName() );
        }

        return productEntity;
    }

    @Override
    public Product mapToProduct(ProductEntity p) {
        if ( p == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        if ( p.getProductId() != null ) {
            product.productId( p.getProductId() );
        }
        if ( p.getName() != null ) {
            product.name( p.getName() );
        }

        return product.build();
    }
}
