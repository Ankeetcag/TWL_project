package com.cag.twowheeler.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MainDealerDetailsDto {

    private String mainDealerID;
	private String mainDealerName;
	private String mainDealerManufacturerName;
	private String mainDealerMailID;
	private long mainDealerContactNumber;
	private long mainDealerAlternateContactNumber;
	private String mainDealerContactPersonName;
	private String contactPersonMobile;
	private String mainDealerPanNumber;
	private String mainDealerGstNumber;
	private String mainDealerBankName;
	private String mainDealerBankBranchName;
	private long mainDealerBankAccNumber;
	private String mainDealerIfsc;
	private String mainDealerAccountHolderName;
	private String mainDealerStatus;
	private String mainDealerActivationStatus;
	private String mainDealerPaniniCheck;
	private LocalDate mainDealerActivationData;
	private LocalDate mainDealerExpireData;
	private String state;
	private String district;
	private String city;
	private String pinCode;
	private String addressDetails;
	
	private List<SubDealerDetailsDto> subDealerDetailsDtos;

}
