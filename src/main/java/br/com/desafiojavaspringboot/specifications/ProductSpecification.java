package br.com.desafiojavaspringboot.specifications;

import br.com.desafiojavaspringboot.entities.Product;
import br.com.desafiojavaspringboot.vos.ProductFilterVO;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ProductSpecification implements Specification<Product> {

    private ProductFilterVO filter;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if((filter.getMinPrice() != null) || (filter.getMaxPrice() != null)){
            predicates.add(builder.between(root.get("price"), filter.getMinPrice(), filter.getMaxPrice()));
        }

        if(filter.getQ() != null){
            Predicate predicateDescription = builder.like(root.get("description"),"%"+filter.getQ()+"%");
            Predicate predicateName = builder.like(root.get("name"),"%"+filter.getQ()+"%");
            predicates.add(builder.or(predicateDescription,predicateName));
        }

        return query.where(builder.and(predicates.toArray(new Predicate[0]))).getRestriction();
    }
}
