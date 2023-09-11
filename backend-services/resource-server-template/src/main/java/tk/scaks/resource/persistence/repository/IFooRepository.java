package tk.scaks.resource.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import tk.scaks.resource.persistence.model.Foo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IFooRepository extends PagingAndSortingRepository<Foo, Long>, CrudRepository<Foo, Long> {
}
