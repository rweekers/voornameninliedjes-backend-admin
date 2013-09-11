package nl.flamingostyle.quooc.service;

import nl.flamingostyle.quooc.domain.Person;

public interface PersonService {
	public Person getRandom();
	public Person getById(Long id);
	public void save(Person person);
}
