package com.stefanini.app.service;

import com.stefanini.app.entity.Asset;
import com.stefanini.app.utils.AssetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public String formatMessage(String employee ,AssetType type, String asset){
        return String.format("%s, informamos que o %s %s está disponível para retirada na sala de suporte (2° andar).\n" +
                "Por gentileza, entrar em contato no telefone 0800-7291929 opção 5  - Digite 3  (falar com o Field para agendamento). (Horários:  8h30 às 12h e 14 às 16h.)\n" +
                "Endereço: Setor Comercial Norte Q 1 BL A - Asa Norte, Brasília - DF, CEP 70711-900-Edifício Number One", employee, type, asset);
    }

    public void SendEmail(Asset asset){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gmnascimento@stefanini.com");
        message.setText(formatMessage(asset.getEmployee().getName(), asset.getAssetType(), asset.getHeritage()));
        message.setSubject("Retirada de equipamento na filial de Brasília!");
        message.setTo(asset.getEmployee().getEmail());
        message.setReplyTo("tibsb@stefanini.com");
        emailSender.send(message);
    }

}
