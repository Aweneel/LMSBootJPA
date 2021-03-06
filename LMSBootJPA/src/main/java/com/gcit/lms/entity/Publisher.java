/**
 * 
 */
package com.gcit.lms.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author awene
 *
 */
@Entity
@Table(name="tbl_publisher", catalog="library")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="pubName", scope=Book.class)
public class Publisher {
	
	@Column(name = "publisherId")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer publisherId;
	
	@Column(name = "publisherName")
	@NotEmpty
	@Length(max=45)
	private String pubName;
	
	@Column(name = "publisherPhone")
	@Length(max=20)
	private String pubPhone;
	
	@Column(name = "publisherAddress")
	@Length(max=55)	
	private String pubAddress;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "publisher")
	private List<Book> books;
	/**
	 * @return the publisherId
	 */
	public Integer getPublisherId() {
		return publisherId;
	}
	/**
	 * @param publisherId the publisherId to set
	 */
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	/**
	 * @return the pubName
	 */
	public String getPubName() {
		return pubName;
	}
	/**
	 * @param pubName the pubName to set
	 */
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}
	/**
	 * @return the pubPhone
	 */
	public String getPubPhone() {
		return pubPhone;
	}
	/**
	 * @param pubPhone the pubPhone to set
	 */
	public void setPubPhone(String pubPhone) {
		this.pubPhone = pubPhone;
	}
	/**
	 * @return the pubAddress
	 */
	public String getPubAddress() {
		return pubAddress;
	}
	/**
	 * @param pubAddress the pubAddress to set
	 */
	public void setPubAddress(String pubAddress) {
		this.pubAddress = pubAddress;
	}
	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}
	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
