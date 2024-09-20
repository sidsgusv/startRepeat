package org.example.start.service;

import org.example.start.exceptions.DuplicateEntityException;
import org.example.start.exceptions.EntityNotFoundException;
import org.example.start.exceptions.UnauthorizedOperationException;
import org.example.start.model.Beer;
import org.example.start.model.User;
import org.example.start.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService {
    private  final BeerRepository beerRepository;
@Autowired
    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }


    @Override
    public List<Beer> getAll(String name, String styleName, String sortBy, String sortOrder) {
        return beerRepository.getAll(name, styleName, sortBy, sortOrder);
  }

  @Override
  public Beer getById(int id) {
        return beerRepository.getById(id);
  }




    @Override
  public void create(Beer beer,User user) {
        //Да няма две бири с еднакви имена

      boolean duplicateExist=true;

      try {
          beerRepository.getByName(beer.getName());
      } catch (EntityNotFoundException e) {
          duplicateExist = false;
      }

      if (duplicateExist) {
          throw new DuplicateEntityException("Beer", "name", beer.getName());
      }
        beer.setCreatedBy(user);
      beerRepository.create(beer);
  }


  @Override
  public void update(Beer beer, User user) {
    if (!user.isAdmin() && !beer.getCreatedBy().equals(user)){
        throw new UnauthorizedOperationException("Only admins can modify beer");
    }
        boolean duplicateExist=true;

        try {
            Beer existingBeer=beerRepository.getByName(beer.getName());

            if (existingBeer.getId()==beer.getId()) {
                duplicateExist=false;
            }

        } catch (EntityNotFoundException e){
            duplicateExist=false;
        }

        if (duplicateExist){
            throw new DuplicateEntityException("Beer", "name", beer.getName());
        }


        beerRepository.update(beer);
  }


  @Override
  public void delete(int id, User user) {
    Beer beer=beerRepository.getById(id);
      if (!user.isAdmin()&&!beer.getCreatedBy().equals(user)){
          throw new UnauthorizedOperationException("Only admins can delete beer");
      }
        beerRepository.delete(id);
  }

    @Override
    public int getNextBeerId() {
        return beerRepository.getNextBeerId();
    }


}
