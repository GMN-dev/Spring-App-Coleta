package com.stefanini.app.service;

import com.stefanini.app.entity.Asset;
import com.stefanini.app.utils.AssetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public String formatMessage(String employee ,AssetType type, String asset){
        return String.format("%s, informamos que o %s %s está disponível para retirada na sala de suporte (2° andar).\n" +
                "Por gentileza, entrar em contato no telefone 0800-7291929 opção 5  - Digite 3  (falar com o Field para agendamento). (Horários:  8h30 às 12h e 14 às 16h.)\n" +
                "Endereço: Setor Comercial Norte Q 1 BL A - Asa Norte, Brasília - DF, CEP 70711-900-Edifício Number One", employee, type, asset);
    }

    public String schedulingMessage(String employee ,AssetType type, String heritage, LocalDateTime scheduledDate){
        return String.format(
                "Olá, %s! \n" +
                "Tudo bem com você?  \n" +
                "Estamos muito felizes em ter você conosco na Stefanini! Para que você comece sua jornada da melhor forma, já preparamos o seu equipamento.  \n" +
                "Aqui estão os detalhes:  \n" +
                "Patrimônio: %s   \n" +
                "Endereço: Setor Comercial Norte Q 1 BL A - Asa Norte, Brasília - DF, CEP 70711-900-Edifício Number One\n" +
                "Quando: %s \n" +
                "Horário: %s \n" +
                "Endereço: Setor Comercial Norte Q 1 BL A - Asa Norte, Brasília - DF, CEP 70711-900-Edifício Number One\n" +
                "Ah, e para garantir que você leve seu equipamento com conforto, sugerimos trazer uma mochila ou bolsa para transportá-lo com segurança.  \n" +
                "Aqui valorizamos o feedback para sempre evoluirmos!\n" +
                "Sua opinião é super importante, então se puder, deixe um comentário sobre o serviço clicando no link. https://forms.office.com/r/3PjEsAckJ3?origin=lprLink \n" +
                "Para começar a se sentir em casa, também anexamos um vídeo de boas-vindas. Não deixe de conferir!  \n" +
                "https://stefaninilatam.sharepoint.com/:v:/r/sites/knowledgebase/Shared%20Documents/Workplace/Onboarding%20TI%20V3.mp4?csf=1&web=1&e=XPBaJK\n" +
                "Estamos aqui para o que precisar. \n" +
                "Abraços,  \n" +
                "Time Workplace Stefanini\n",
                employee, heritage, scheduledDate.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), scheduledDate.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
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
