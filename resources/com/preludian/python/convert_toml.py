#!/bin/env python3
import argparse, json, toml


def execute(input_file, output_file):
    toml_file = toml.load(input_file)

    if output_file == 'STDOUT':
        print(json.dumps(toml_file), end='')

    else:
        print('Python Util - Toml to JSON')
        print(f'Processing {input_file} -> {output_file}')

        with open(output_file, 'w') as f:
            json.dump(toml_file, f)

            print('File saved sucessfully')


if __name__ == '__main__':

    parser = argparse.ArgumentParser(description='Convert a Toml file to JSON. Useful for parsing pyproject.toml')

    parser.add_argument('--input', required=True, type=str, help='File to be read (e.g. pyproject.toml)')
    parser.add_argument('--output',required=True, type=str, help='File to be written (e.g. pyproject.json)')

    args = parser.parse_args()

    execute(args.input, args.output)
