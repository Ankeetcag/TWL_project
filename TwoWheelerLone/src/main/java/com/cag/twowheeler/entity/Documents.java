package com.cag.twowheeler.entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Documents {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private String DocumentID;
	private String fileName;
	private String fileType; 
	@Lob
	private byte[] data;
	
	private long fileLength;
	
	@ManyToOne
	@JoinColumn(name = "MainDealerID")
	private MainDealer mainDealer;
	
	@ManyToOne
	@JoinColumn(name = "SubDealerID")
	private SubDealer subDealer;

}
