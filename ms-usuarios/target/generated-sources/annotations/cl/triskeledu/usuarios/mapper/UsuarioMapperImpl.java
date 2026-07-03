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
    date = "2026-07-02T22:03:43-0400",
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

        usuario.nickname( request.getNickname() );
        usuario.email( request.getEmail() );
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

        if ( usuario.getId() != null ) {
            usuarioResponse.id( usuario.getId().longValue() );
        }
        usuarioResponse.nickname( usuario.getNickname() );
        usuarioResponse.email( usuario.getEmail() );
        usuarioResponse.rol( usuario.getRol() );
        usuarioResponse.activo( usuario.getActivo() );

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

        usuario.setNickname( request.getNickname() );
        usuario.setEmail( request.getEmail() );
        usuario.setPassword( request.getPassword() );
        usuario.setRol( request.getRol() );
    }
}
