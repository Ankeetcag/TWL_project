package com.cag.twowheeler.entity;
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "vehicalPriceiD")
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicalPrice {

	
	@Id
	private String vehicalPriceID;
	@Column(name ="OnRoadPrice")
	private double vehicalOnRoadPrice;
	@Column(name ="MaxLoanAmount")
	private double vehicalMaxLoanAmount;
	private String state;
	
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)//,fetch = FetchType.EAGER)
	@JoinColumn(name = "vehicalvariant_Id")
	private VehicalVariant type;

	@ToString.Exclude
	@ManyToOne//(cascade = CascadeType.ALL)//,fetch = FetchType.EAGER)
	@JoinColumn(name="Vehical_oemid")
//	@JsonBackReference
	private VehicalOem oem;
	
	@ManyToMany(mappedBy = "vehicleVeriants")
	private List<SubDealer> subDealers;
	
	@ManyToMany(mappedBy = "Veriants")
	private List<MainDealer> mainDealers;
}
