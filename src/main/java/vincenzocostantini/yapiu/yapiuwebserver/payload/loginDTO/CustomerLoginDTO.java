package vincenzocostantini.yapiu.yapiuwebserver.payload.loginDTO;

public record CustomerLoginDTO(

        Integer tavNum,
        String nome,
        String cognome,
        Integer paymentNumber

) {
}
