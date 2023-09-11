package tk.scaks.resource.service;

import tk.scaks.resource.persistence.model.Foo;

import java.util.Optional;


public interface IFooService {
    Optional<Foo> findById(Long id);

    Foo save(Foo foo);
    
    Iterable<Foo> findAll();

}
