package com.dappermoose.stsimplefinance.data;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// TODO: Auto-generated Javadoc
/**
 * The Class YesNoConverter.
 */
@Converter (autoApply = true)
public class YesNoConverter implements AttributeConverter<YesNoEnum, Character>
{
    /*
     * (non-Javadoc)
     *
     * @see
     * jakarta.persistence.AttributeConverter#convertToDatabaseColumn(java.lang
     * .Object)
     */
    @Override
    public Character convertToDatabaseColumn (final YesNoEnum yesNo)
    {
        Character retVal;
        switch (yesNo)
        {
            case YES:
                retVal = Character.valueOf ('Y');
                break;
            case NO:
                retVal = Character.valueOf ('N');
                break;
            default:
                throw new IllegalArgumentException ("Unknown value: "
                        + yesNo.toString ());
        }
        return retVal;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * jakarta.persistence.AttributeConverter#convertToEntityAttribute(java.lang
     * .Object)
     */
    @Override
    @SuppressWarnings ("PMD.UnnecessaryFullyQualifiedName")
    public YesNoEnum convertToEntityAttribute (final Character dbData)
    {
        YesNoEnum retVal;
        switch (dbData)
        {
            case 'Y':
                retVal = YesNoEnum.YES;
                break;
            case 'N':
                retVal = YesNoEnum.NO;
                break;
            default:
                throw new IllegalArgumentException ("Unknown Value: "
                        + dbData.toString ());
        }
        return retVal;
    }
}
