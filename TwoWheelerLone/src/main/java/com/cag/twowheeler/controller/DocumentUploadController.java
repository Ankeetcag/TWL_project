package com.cag.twowheeler.controller;

import java.io.IOException;
import java.io.NotActiveException;
import java.io.OutputStream;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cag.twowheeler.entity.Documents;
import com.cag.twowheeler.entity.MainDealer;
import com.cag.twowheeler.entity.SubDealer;
import com.cag.twowheeler.repository.DocumentRepository;
import com.cag.twowheeler.repository.MainDealerRepository;
import com.cag.twowheeler.repository.SubDealerRepository;
import com.cag.twowheeler.responce.responce;

@RestController
public class DocumentUploadController {

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	MainDealerRepository mainDealerRepository;

	@Autowired
	SubDealerRepository subDealerRepository;

	@PostMapping("/uploadfile")
	public ResponseEntity<responce> uploadFile(@RequestParam MultipartFile file, @RequestParam String ID,
			@RequestParam String documentType) throws IOException {
		if (file != null) {
			// LOGIC FOR CREATING DOCUMENTID BASE ON DEALEAR [EX:->PANCARD*KABAJ-0001-A01]
			String documentID = "";
			try {
				if (ID.substring(11).equalsIgnoreCase("A01")) {

					MainDealer mainDealer = mainDealerRepository.findById(ID).get();

					documentID = documentType + "*" + mainDealer.getMainDealerID();// ID Generated
					Optional<Documents> optationalDocument = documentRepository.findById(documentID);
					if (optationalDocument.isPresent()) {
						Documents existDocument = optationalDocument.get();
						Documents document = Documents.builder().DocumentID(documentID)
								.fileName(file.getOriginalFilename()).data(file.getBytes())
								.fileType(file.getContentType()).fileLength(file.getSize()).mainDealer(mainDealer)
								.build();
						BeanUtils.copyProperties(document, existDocument);// copy all properties to original object!
						documentRepository.save(existDocument);

						return ResponseEntity.status(HttpStatus.OK).body(responce.builder()
								.data(existDocument.getData()).error("FALSE").message("FILE UPLOADED..!").build());

					} else {
						Documents document = Documents.builder().DocumentID(documentID)
								.fileName(file.getOriginalFilename()).data(file.getBytes())
								.fileType(file.getContentType()).fileLength(file.getSize()).mainDealer(mainDealer)
								.build();
						System.out.println(file.getName());
						documentRepository.save(document);

						return ResponseEntity.status(HttpStatus.OK).body(responce.builder().data(document.getData())
								.error("FALSE").message("FILE UPLOADED..!").build());
					}

				} else {
					SubDealer subDealer = subDealerRepository.findById(ID).get();
					String subDealerID = subDealer.getSubDealerID();
					documentID = documentType + "*" + subDealerID;// ID Generated

					Optional<Documents> optationalDocument = documentRepository.findById(documentID);
					if (optationalDocument.isPresent()) {
						Documents existDocument = optationalDocument.get();
						Documents document = Documents.builder().DocumentID(documentID)
								.fileName(file.getOriginalFilename()).data(file.getBytes())
								.fileType(file.getContentType()).fileLength(file.getSize()).subDealer(subDealer)
								.build();
						BeanUtils.copyProperties(document, existDocument);// copy all properties to original object!
						documentRepository.save(existDocument);

						return ResponseEntity.status(HttpStatus.OK).body(responce.builder()
								.data(existDocument.getData()).error("FALSE").message("FILE UPLOADED..!").build());
					} else {
						Documents document = Documents.builder().DocumentID(documentID)
								.fileName(file.getOriginalFilename()).data(file.getBytes())
								.fileType(file.getContentType()).fileLength(file.getSize()).subDealer(subDealer)
								.build();
						documentRepository.save(document);

						return ResponseEntity.status(HttpStatus.OK).body(responce.builder().data(document.getData())
								.error("FALSE").message("FILE UPLOADED..!").build());
					}
				}
			} catch (Exception e) {
				// exception handle By Return Statement...!
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(responce.builder().error("FALSE").message("FILE NOT UPLOADED..!").build());
	}

	@GetMapping("/getfile")
	public ResponseEntity<responce> getFile(@RequestParam String mainDealerID, @RequestParam String documentType,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Documents documents = null;
		try {
			documents = documentRepository.findById(documentType + "*" + mainDealerID).get();
		} catch (Exception e) {
			// TODO: handle exception
			// exception handle by return Statement...!
		}
		if (documents != null) {
			System.out.println(documents);
			response.setContentType(documents.getFileType());
			String fileType = documents.getFileName();
			String fileExtension = "";
			if (fileType != null & fileType.contains(".")) {
				String[] split = fileType.split("\\.");
				fileExtension = '.' + split[1];
			} else if (fileType != null) {
				fileExtension = ".bin";
			}

			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + mainDealerID + "[" + documentType + "]" + fileExtension + "\"");
			response.setContentLength((int) documents.getFileLength());

			// Write the file data to the response's output stream
			OutputStream out = response.getOutputStream();
			out.write(documents.getData());
			out.flush();
			out.close();

			return ResponseEntity.status(HttpStatus.OK)
					.body(responce.builder().data(documents.getData()).error("FALSE").message("FILE FIND..!").build());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(responce.builder().error("True").message("FILE NOT Avaliable..!").build());
		}
	}
}
