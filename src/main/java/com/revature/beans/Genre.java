package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Genre")
public class Genre {
	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genreSequence")
	  @SequenceGenerator(allocationSize = 1, name = "genreSequence", sequenceName = "SQ_GENRE_PK")
	  @Column(name = "GENRE_ID")
	  private int genreId;
	  @Column(name = "NAME")
	  private String name;
	  @ManyToMany(mappedBy = "genres")
	    private Set<Book> books = new HashSet<>();
	
}
