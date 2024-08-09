package com.venda.app.services;

import com.venda.app.entities.Client;
import com.venda.app.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public String save(Client client) {
        this.clientRepository.save(client);
        return "Cliente salvo com susexo";
    }

    public String update(Client clientUpdated, long id) {
        Optional<Client> clientOptional =
                this.clientRepository.findById(id);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setName(
                    clientUpdated.getName() != null ?
                            clientUpdated.getName() :
                            client.getName()
            );
            client.setAge(
                    clientUpdated.getAge() != 0 ?
                            clientUpdated.getAge() :
                            client.getAge()
            );
            client.setEmail(
                    clientUpdated.getEmail() != null ?
                            clientUpdated.getEmail() :
                            client.getEmail()
            );
            client.setAddress(
                    clientUpdated.getAddress() != null ?
                            clientUpdated.getAddress() :
                            client.getAddress()
            );
            client.setCellphone(
                    clientUpdated.getCellphone() != null ?
                            clientUpdated.getCellphone() :
                            client.getCellphone()
            );
            this.clientRepository.save(client);
        }
        return "Atualizado com sucesso";
    }

    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    public Optional<Client> findById(long id) {
        if (this.clientRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Cliente não achado " + id);
        }
        return this.clientRepository.findById(id);
    }

    public String deleteById(Long id) {
        if (this.clientRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Client não encontrado. Id: " + id);
        }
        this.clientRepository.deleteById(id);
        return "Cliente deletado";
    }
}
