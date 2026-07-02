package cl.triskeledu.usuarios.mapper;

import cl.triskeledu.usuarios.dto.UsuarioRequest;
import cl.triskeledu.usuarios.dto.UsuarioResponse;
import cl.triskeledu.usuarios.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-02T18:03:30-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.100.v20260624-0231, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(UsuarioRequest request) {
        if ( request == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        usuario.email( request.getEmail() );
        usuario.nickname( request.getNickname() );
        usuario.password( request.getPassword() );
        usuario.rol( request.getRol() );

        return usuario.build();
    }

    @Override
    public UsuarioResponse toResponse(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioResponse.UsuarioResponseBuilder usuarioResponse = UsuarioResponse.builder();

        usuarioResponse.activo( usuario.getActivo() );
        usuarioResponse.email( usuario.getEmail() );
        if ( usuario.getId() != null ) {
            usuarioResponse.id( usuario.getId().longValue() );
        }
        usuarioResponse.nickname( usuario.getNickname() );
        usuarioResponse.rol( usuario.getRol() );

        return usuarioResponse.build();
    }

    @Override
    public List<UsuarioResponse> toResponseList(List<Usuario> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<UsuarioResponse> list = new ArrayList<UsuarioResponse>( usuarios.size() );
        for ( Usuario usuario : usuarios ) {
            list.add( toResponse( usuario ) );
        }

        return list;
    }

    @Override
    public void updateEntity(UsuarioRequest request, Usuario usuario) {
        if ( request == null ) {
            return;
        }

        usuario.setEmail( request.getEmail() );
        usuario.setNickname( request.getNickname() );
        usuario.setPassword( request.getPassword() );
        usuario.setRol( request.getRol() );
    }
}
