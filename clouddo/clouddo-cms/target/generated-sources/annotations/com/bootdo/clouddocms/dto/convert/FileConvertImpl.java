package com.bootdo.clouddocms.dto.convert;

import com.bootdo.clouddocms.domain.FileDO;
import com.bootdo.clouddocms.dto.FileDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-09-25T12:00:50+0800",
    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
public class FileConvertImpl implements FileConvert {

    @Override
    public FileDTO do2dto(FileDO person) {
        if ( person == null ) {
            return null;
        }

        FileDTO fileDTO = new FileDTO();

        fileDTO.setId( person.getId() );
        fileDTO.setType( person.getType() );
        fileDTO.setUrl( person.getUrl() );
        fileDTO.setCreateDate( person.getCreateDate() );

        return fileDTO;
    }

    @Override
    public List<FileDTO> dos2dtos(List<FileDO> list) {
        if ( list == null ) {
            return null;
        }

        List<FileDTO> list_ = new ArrayList<FileDTO>();
        for ( FileDO fileDO : list ) {
            list_.add( do2dto( fileDO ) );
        }

        return list_;
    }
}
