package com.cag.twowheeler.dto;

import java.time.LocalDate;

import com.cag.twowheeler.entity.MainDealer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubDealerDetailsDto {

//	private String mainDealerID;
	private String subDealerID;
	private String subDealerName;
	private String subDealerManufacturerName;
	private String subDealerMailID;
	private long subDealerContactNumber;
	private long subDealerAlternateContactNumber;
	private String subDealerContactPersonName;
	private String contactPersonMobile;
	private String subDealerPanNumber;
	private String subDealerGstNumber;
	private String subDealerBankName;
	private String subDealerBankBranchName;
	private long subDealerBankAccNumber;
	private String subDealerIfsc;
	private String subDealerAccountHolderName;
	private String subDealerStatus;
	private String subDealerActivationStatus;
	private String subDealerPaniniCheck;
	private LocalDate subDealerActivationData;
	private LocalDate subDealerExpireData;
	private String state;
	private String district;
	private String region;
	private String area;
	private String city;
	private String pinCode;
	private String addressDetails;

	// Only use for while adding Sub Dealer
	private MainDealer mainDealer;

}
