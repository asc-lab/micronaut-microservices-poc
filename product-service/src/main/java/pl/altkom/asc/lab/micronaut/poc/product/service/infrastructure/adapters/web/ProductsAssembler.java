package pl.altkom.asc.lab.micronaut.poc.product.service.infrastructure.adapters.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.CoverDto;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.ProductDto;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.Cover;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class ProductsAssembler {

    static List<ProductDto> map(List<Product> products) {
        return products.stream()
                .map(ProductsAssembler::map)
                .collect(Collectors.toList());
    }

    static ProductDto map(Product product) {
        return  ProductDto.builder()
                .code(product.getCode())
                .name(product.getName())
                .image(product.getImage())
                .description(product.getDescription())
                .covers(product.getCovers().stream()
                        .map(ProductsAssembler::mapCover)
                        .collect(Collectors.toList()))
                //.questions(product.getQuestions().stream()
                //.map(ProductsAssembler::mapQuestion)
                //.collect(Collectors.toList()))
                .maxNumberOfInsured(product.getMaxNumberOfInsured())
                .build();
    }

    private static CoverDto mapCover(Cover cover) {
        return new CoverDto(
                cover.getCode(),
                cover.getName(),
                cover.getDescription(),
                cover.isOptional(),
                cover.getSumInsured()
        );
    }
}
