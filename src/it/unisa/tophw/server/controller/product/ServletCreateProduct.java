package it.unisa.tophw.server.controller.product;


import it.unisa.tophw.server.model.beans.BrandBean;
import it.unisa.tophw.server.model.beans.CatalogBean;
import it.unisa.tophw.server.model.beans.ProductBean;
import it.unisa.tophw.server.model.management.BrandDAO;
import it.unisa.tophw.server.model.management.CatalogDAO;
import it.unisa.tophw.server.model.management.ProductDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


    @MultipartConfig
@WebServlet(name = "ServletCreateProduct")
public class ServletCreateProduct extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "products";

    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String nome = "";
        String descrizione_b = "";
        String descrizione = "";
        Double prezzo = 0.0;
        String stringId_marca = "";
        String stringId_catalogo = "";
        int perc_sconto = 0;
        int quantita = 0;
        int id_marca = 0;
        int id_catalogo = 0;
        String pathImg1="",pathImg2="",pathImg3="";




        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }

        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        // this path is relative to application's directory

        String uploadPath = getServletContext().getRealPath(File.separator + "images")
                + File.separator + UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {


            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if ( !item.isFormField()) {
                        if(item!=null & item.getName()!=null && item.getName().length()>0){
                            String fileName = new File(item.getName()).getName();
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);

                            switch (item.getFieldName()){
                                case "img1" : pathImg1 = fileName.toString();
                                    break;
                                case "img2" : pathImg2 = fileName.toString();
                                    break;
                                case "img3" : pathImg3 = fileName.toString();
                                    break;
                                default : break;
                            }
                            // saves the file on disk
                            item.write(storeFile);
                            System.out.println("Immagine "+ fileName +" salvato con successo ");
                        }
                    } else {

                        switch (item.getFieldName()) {

                            case "Nome_Prodotto":
                                nome = item.getString();
                                break;
                            case "Descrizione_Breve":
                                descrizione_b = item.getString();
                                break;
                            case "Descrizione":
                                descrizione = item.getString();
                                break;
                            case "Prezzo":
                                prezzo = Double.parseDouble(item.getString() != null && item.getString().length() > 0 ? item.getString() : "0");
                                break;
                            case "selectMarca":
                                stringId_marca = item.getString();
                                break;
                            case "selectCatalog":
                                stringId_catalogo = item.getString();
                                break;
                            case "perc_sconto":
                                perc_sconto = Integer.parseInt(item.getString() != null && item.getString().length() > 0 ? item.getString() : "0");
                                break;
                            case "quantita":
                                quantita = Integer.parseInt(item.getString() != null && item.getString().length() > 0 ? item.getString() : "0");
                                break;
                            default:
                                System.out.println(item.getString());
                                break;

                        }

                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        boolean error = false;
        String msgOutput = "";

        if (stringId_catalogo != null && stringId_catalogo.length() > 0)
            id_catalogo = Integer.parseInt(stringId_catalogo);
        if (stringId_marca != null && stringId_marca.length() > 0)
            id_marca = Integer.parseInt(stringId_marca);

        ProductBean prodotto = new ProductBean();

        if (nome != null && nome.length() > 0) {

            prodotto.setNome(nome);
            prodotto.setDescrizione_breve(descrizione_b);
            prodotto.setDescrizione_estesa(descrizione);
            prodotto.setPrezzo(prezzo);
            prodotto.setQuantita(quantita);
            prodotto.setPerc_sconto(perc_sconto);

            if(pathImg1!=null && pathImg1.length()>0)
                prodotto.setPathImg1(pathImg1);
            if(pathImg2!=null && pathImg2.length()>0)
                prodotto.setPathImg2(pathImg2);
            if(pathImg3!=null && pathImg3.length()>0)
                prodotto.setPathImg3(pathImg3);

            BrandBean brand = new BrandBean();
            BrandDAO brandDAO = new BrandDAO();

            if (id_marca > 0) {
                brand = brandDAO.doRetriveByKey(id_marca);
                if (brand != null && brand.getIdMarca() > 0)
                    prodotto.setId_marca(brand.getIdMarca());
            }


            CatalogBean catalogBean = new CatalogBean();
            CatalogDAO catalogDAO = new CatalogDAO();

            if (id_catalogo > 0) {
                catalogBean = catalogDAO.doRetriveByKey(id_catalogo);
                if (catalogBean != null && catalogBean.getId_catalogo() > 0)
                    prodotto.setId_catalogo(catalogBean.getId_catalogo());

            }


            ProductDAO productDAO = new ProductDAO();
            if (productDAO.doSave(prodotto)) {
                msgOutput = "Prodotto Inserito Correttamente";
            } else {
                msgOutput = "Errore durante l'inserimento del prodotto";
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminPanel.jsp?msgOutputCreateProduct=" + msgOutput + "");
            dispatcher.forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
